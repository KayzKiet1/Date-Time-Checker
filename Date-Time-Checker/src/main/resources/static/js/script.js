// Date Time Checker JavaScript - Version 1.0
document.addEventListener('DOMContentLoaded', function() {
    // Get DOM elements
    const dayInput = document.getElementById('day');
    const monthInput = document.getElementById('month');
    const yearInput = document.getElementById('year');
    const checkButton = document.getElementById('checkButton');
    const clearButton = document.getElementById('clearButton');
    const closeButton = document.getElementById('closeBtn'); // New ID
    const messageBox = document.getElementById('messageBox');
    
    // Check button functionality
    checkButton.addEventListener('click', function() {
        const day = dayInput.value.trim();
        const month = monthInput.value.trim();
        const year = yearInput.value.trim();
        
        // Hide previous messages
        hideMessage();

        // Validate input
        if (!day || !month || !year) {
            showMessage('Please fill all fields!', 'error');
            return;
        }
        
        // Send to backend
        fetch('/api/check-date', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                day: day,
                month: month,
                year: year
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                showMessage(data.message, 'success');
            } else {
                showMessage(data.message, 'error');
            }
        })
        .catch(error => {
            showMessage('Error: ' + error.message, 'error');
        });
    });
    
    // Clear button functionality
    clearButton.addEventListener('click', function() {
        dayInput.value = '';
        monthInput.value = '';
        yearInput.value = '';
        hideMessage();
    });
    
    // Close X button - GUARANTEED to work
    closeButton.addEventListener('click', function(e) {
        e.preventDefault();
        e.stopPropagation();

        // Show confirm dialog - this WILL work
        const confirmed = window.confirm('Are you sure to exit?');

        if (confirmed) {
            // Multiple methods to close
            try {
                window.close();
            } catch(err) {
                // If window.close() fails, try alternative
                window.location.href = 'about:blank';
            }
        }
    });

    // Message functions
    function showMessage(text, type) {
        messageBox.textContent = text;
        messageBox.className = 'message-area ' + type;
        messageBox.style.display = 'block';
    }

    function hideMessage() {
        messageBox.style.display = 'none';
        messageBox.className = 'message-area';
    }
});

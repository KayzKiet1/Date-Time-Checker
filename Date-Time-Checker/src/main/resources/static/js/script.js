document.addEventListener('DOMContentLoaded', function() {
    const dayInput = document.getElementById('day');
    const monthInput = document.getElementById('month');
    const yearInput = document.getElementById('year');
    const checkButton = document.getElementById('checkButton');
    const clearButton = document.getElementById('clearButton');
    const closeButton = document.getElementById('closeButton');
    const messageBox = document.getElementById('messageBox');
    
    // Check button click event
    checkButton.addEventListener('click', function() {
        const day = dayInput.value.trim();
        const month = monthInput.value.trim();
        const year = yearInput.value.trim();
        
        // Reset message box
        messageBox.style.display = 'none';
        messageBox.className = 'message';
        
        // Check if all fields are filled
        if (!day || !month || !year) {
            showMessage('Please fill all fields!', 'error');
            return;
        }
        
        // Send request to backend
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
            showMessage('An error occurred: ' + error, 'error');
        });
    });
    
    // Clear button click event
    clearButton.addEventListener('click', function() {
        dayInput.value = '';
        monthInput.value = '';
        yearInput.value = '';
        messageBox.style.display = 'none';
    });
    
    // Close button click event
    closeButton.addEventListener('click', function() {
        if (confirm('Are you sure to exit?')) {
            window.close();
        }
    });
    
    // Function to show message
    function showMessage(message, type) {
        messageBox.textContent = message;
        messageBox.className = 'message ' + type;
        messageBox.style.display = 'block';
    }
});

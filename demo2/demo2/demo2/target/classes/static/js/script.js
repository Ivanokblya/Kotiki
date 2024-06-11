document.addEventListener('DOMContentLoaded', function() {
    const userList = document.getElementById('userList');
    const addUserForm = document.getElementById('addUserForm');

    // Функция для получения списка пользователей
    function fetchUsers() {
        fetch("/users")
            .then(response => response.json())
            .then(data => {
                userList.innerHTML = '';
                data.forEach(user => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>${user.active}</td>
                    `;
                    userList.appendChild(row);
                });
            })
            .catch(error => console.error('Error fetching users:', error));
    }

    // Функция для добавления нового пользователя
    addUserForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(addUserForm);
        const user = {
            firstName: formData.get('firstName'),
            lastName: formData.get('lastName'),
            email: formData.get('email'),
            password: formData.get('password'),
            active: true
        };

        fetch("\"user\"", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
        .then(response => response.json())
        .then(data => {
            console.log('User added:', data);
            fetchUsers();
            addUserForm.reset();
        })
        .catch(error => console.error('Error adding user:', error));
    });

    // Загрузка списка пользователей при загрузке страницы
    fetchUsers();
});

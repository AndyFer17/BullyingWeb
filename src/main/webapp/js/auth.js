// Encapsular funcionalidad dentro de una función autoinvocada
(function () {
    const container = document.getElementById('container');
    const registerBtn = document.getElementById('register');
    const loginBtn = document.getElementById('login');

    if (registerBtn && loginBtn) {
        // Cambiar al formulario de registro
        registerBtn.addEventListener('click', () => {
            container.classList.add("active");
        });

        // Cambiar al formulario de login
        loginBtn.addEventListener('click', () => {
            container.classList.remove("active");
        });
    }
})();

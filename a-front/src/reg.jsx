import React from 'react';
import { useNavigate } from 'react-router-dom'; // Импортируем useNavigate

import './aut.css'; 

const RegPage = () => {
    const navigate = useNavigate(); // Получаем функцию для навигации

    const handleRegistration = (event) => {
        event.preventDefault(); // Предотвращаем перезагрузку страницы

        // Здесь можно добавить логику для обработки регистрации, например:
        // - Валидация данных
        // - Отправка данных на сервер
        // - Обработка ответа сервера

        // После успешной регистрации перенаправляем на страницу /start
        navigate('/start');
    };

    const handleLoginRedirect = () => {
        navigate('/'); // Путь к странице авторизации
    };

    return (
        <div className="auth-body">
            <div className="auth-container">
                <h2 className="custom-header">Регистрация</h2>
                <form onSubmit={handleRegistration}>
                    <div className="auth-form-group">
                        <label htmlFor="username">Логин</label>
                        <input type="text" id="username" required />
                    </div>
                    <div className="auth-form-group">
                        <label htmlFor="email">Почта</label>
                        <input type="email" id="email" required />
                    </div>
                    <div className="auth-form-group">
                        <label htmlFor="password">Пароль</label>
                        <input type="password" id="password" required />
                    </div>
                    <button type="submit" className="auth-button">Зарегистрироваться</button>
                </form>
                <div className="auth-footer">
                    <p>Уже есть аккаунт? <a onClick={handleLoginRedirect} style={{ cursor: 'pointer', color: 'blue' }}>Войти</a></p>
                </div>
            </div>
        </div>
    );
};

export default RegPage;
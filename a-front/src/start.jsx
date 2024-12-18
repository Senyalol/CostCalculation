import React from 'react';
import { useNavigate } from 'react-router-dom'; // Для навигации
import './start.css'; // Импорт стилей

const StartPage = () => {
    const navigate = useNavigate();

    // Обработчики кнопок
    const handleAboutClick = () => {
        navigate('/about'); // Переход на страницу "О нас"
    };

    const handleCalculationClick = () => {
        navigate('/calculation'); // Переход на страницу "Калькуляция"
    };

    const handleHomeClick = () => {
        navigate('/'); // Переход на главную страницу
    };

    return (
        <div className="background"> {/* Добавлен класс для фона */}
            <div className="start-body">
                {/* Контейнер для заголовка */}
                <div className="start-header">
                    <h1 className="start-title">Добро пожаловать!</h1>
                    <p className="start-description">
                        Это ваш универсальный инструмент для расчётов. Надеемся, что вы останетесь довольны опытом использования нашего сервиса!
                    </p>
                </div>

                {/* Контейнер для карточек */}
                <div className="start-cards">
                    <div className="start-card" onClick={handleAboutClick}>
                        <h3>О нас</h3>
                        <p>Узнайте больше о нашей компании, её миссии и ценностях.</p>
                    </div>
                    <div className="start-card" onClick={handleCalculationClick}>
                        <h3>Калькуляция</h3>
                        <p>Используйте наш инструмент для точных и удобных расчётов.</p>
                    </div>
                </div>

               
                {/* Футер */}
                <footer className="start-footer">
                    <p>© 2024 Все права защищены.</p>
                </footer>
            </div>
        </div>
    );
};

export default StartPage;

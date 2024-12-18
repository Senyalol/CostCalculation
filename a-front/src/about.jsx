import React from 'react';
import { useNavigate } from 'react-router-dom';
import './about.css';

function AboutPage() {
    const navigate = useNavigate();

    // Обработчик кнопки для возврата на страницу /start
    const handleBackClick = () => {
        navigate('/');
    };

    return (
        <div className="about-background"> {/* Применили новый класс для фона */}
            <div className="about-body">
                <div className="about-container">
                    <h1 className="about-title">О нашем сервисе</h1>
                    <p className="about-text">
                        Наш сервис предоставляет удобный инструмент для выполнения различных вычислений. 
                        Мы стремимся сделать ваши задачи проще, предоставляя интуитивно понятный интерфейс и быстрые результаты. 
                        Используйте наш калькулятор для расчётов, будь то финансы, математика или что-либо ещё.
                    </p>
                    <button className="about-button" onClick={handleBackClick}>
                        Вернуться на главную
                    </button>
                </div>
            </div>
        </div>
    );
}

export default AboutPage;

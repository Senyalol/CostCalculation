import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './calc.css';

function CalcPage() {
  const [dateTime, setDateTime] = useState(new Date());
  const [selectedCurrency, setSelectedCurrency] = useState('USD');
  const [exchangeRates, setExchangeRates] = useState({
    USD: 3.4,
    EUR: 0.85,
    RUB: 75.0,
    BYN: 2.5,
  });
  const [manualRates, setManualRates] = useState({
    USD: '',
    EUR: '',
    RUB: '',
    BYN: '',
  });
  const [period, setPeriod] = useState(0);
  const [materials, setMaterials] = useState([]);
  const [service, setService] = useState(null);
  const [expenses, setExpenses] = useState([]);
  const [otherExpenses, setOtherExpenses] = useState('');
  const [totalMaterialCost, setTotalMaterialCost] = useState(0);
  const [totalIndirectCost, setTotalIndirectCost] = useState(0);
  const [totalCost, setTotalCost] = useState(0);
  const [revenue, setRevenue] = useState(0); // Состояние для выручки
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => setDateTime(new Date()), 1000);
    return () => clearInterval(timer);
  }, []);

  const handleCurrencyChange = (event) => {
    setSelectedCurrency(event.target.value);
  };

  const handleManualRateChange = (event) => {
    const { name, value } = event.target;
    setManualRates((prevRates) => ({
      ...prevRates,
      [name]: value,
    }));
  };

  const addMaterial = () => {
    setMaterials([...materials, { material_id: materials.length + 1, name: '', quantity: 0, price: 0 }]);
  };

  const addService = () => {
    if (!service) {
      setService({ product_id: 1, name: '', description: '', quantity: 0, price: 0 });
    }
  };

  const addExpense = () => {
    setExpenses([...expenses, { id: expenses.length + 1, description: '', amount: 0 }]);
  };

  const handleMaterialChange = (index, field, value) => {
    const updatedMaterials = materials.map((material, i) => {
      if (i === index) {
        return { ...material, [field]: value };
      }
      return material;
    });
    setMaterials(updatedMaterials);
  };

  const handleServiceChange = (field, value) => {
    if (service) {
      setService({ ...service, [field]: value });
    }
  };

  const handleExpenseChange = (index, field, value) => {
    const updatedExpenses = expenses.map((expense, i) => {
      if (i === index) {
        return { ...expense, [field]: value };
      }
      return expense;
    });
    setExpenses(updatedExpenses);
  };

  const removeMaterial = (index) => {
    const updatedMaterials = materials.filter((_, i) => i !== index);
    setMaterials(updatedMaterials);
  };

  const removeService = () => {
    setService(null);
  };

  const removeExpense = (index) => {
    const updatedExpenses = expenses.filter((_, i) => i !== index);
    setExpenses(updatedExpenses);
  };

  const calculateTotalCost = () => {
    const totalMaterials = materials.reduce((sum, item) => sum + (item.quantity * item.price || 0), 0);
    const totalService = service ? service.quantity * service.price : 0;
    const totalExpenses = expenses.reduce((sum, item) => sum + (item.amount || 0), 0);
    
    setTotalMaterialCost(totalMaterials);
    setTotalIndirectCost(totalExpenses + (otherExpenses ? parseFloat(otherExpenses) : 0));
    setTotalCost(totalMaterials + totalService + totalIndirectCost);
  };

  const submitExpense = async (expense) => {
    const expenseToSubmit = {
      expenses_id: expense.id,
      description: expense.description,
      amount: expense.amount > 0 ? expense.amount : 0,
    };

    try {
      const response = await fetch('http://localhost:8080/api/expenses', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(expenseToSubmit),
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error('Ошибка от сервера:', errorData);
        throw new Error('Network response was not ok');
      }

      alert('Расход занесён успешно!');
    } catch (error) {
      console.error('Ошибка при занесении расхода:', error);
    }
  };

  const submitMaterial = async (material) => {
    const materialToSubmit = {
      material_id: material.material_id,
      name: material.name,
      costPerUnit: material.price > 0 ? material.price : 0,
      unit: `${material.quantity} грамм`,
    };

    try {
      const response = await fetch('http://localhost:8080/api/materials', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(materialToSubmit),
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error('Ошибка от сервера:', errorData);
        throw new Error('Network response was not ok');
      }

      alert('Материал занесён успешно!');
    } catch (error) {
      console.error('Ошибка при занесении материала:', error);
    }
  };

  const submitCostCalculation = async () => {
    const costCalculationToSubmit = {
        costcalculation_id: 1, 
        product_id: service ? service.product_id : null, // Поставьте здесь правильный product_id
        totalMaterialCost: totalMaterialCost.toFixed(2),
        totalExpenses: totalIndirectCost.toFixed(2), // Здесь используйте totalIndirectCost, если это ваши косвенные расходы
        totalCost: totalCost.toFixed(2), //  totalCost
    };

    try {
        const response = await fetch('http://localhost:8080/api/cost-calculations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(costCalculationToSubmit),
        });

        if (!response.ok) {
            const errorData = await response.json();
            console.error('Ошибка от сервера:', errorData);
            throw new Error('Network response was not ok');
        }

        alert('Расчет успешен!');
    } catch (error) {
        console.error('Ошибка при отправке расчета:', error);
    }
};

  const submitService = async (service) => {
    const serviceToSubmit = {
      product_id: service.product_id,
      name: service.name,
      description: service.description || '',
      price: service.price > 0 ? service.price : 0,
      count: service.quantity > 0 ? service.quantity : 0,
    };

    try {
      const response = await fetch('http://localhost:8080/api/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(serviceToSubmit),
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error('Ошибка от сервера:', errorData);
        throw new Error('Network response was not ok');
      }

      alert('Услуга занесена успешно!');
    } catch (error) {
      console.error('Ошибка при занесении услуги:', error);
    }
  };

  const fetchMaterials = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/materials');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      if (data.length > 0) {
        alert(`Первый ID: ${data[0].material_id}`);
        alert(`граммы: ${data[0].unit}`);
      } else {
        alert('Нет доступных материалов.');
      }
    } catch (error) {
      console.error('Error fetching materials:', error);
    }
  };

  return (
    <div className="calc-page-body">
      <button onClick={() => navigate('/')} className="calc-page-button back-button">Назад</button>
      <div className="calc-page-datetime">
        <p>{dateTime.toLocaleString()}</p>
      </div>
      <div className="calc-page-container">
        <h1 className="calc-page-h1">Финансовый калькулятор</h1>

        <div className="calc-page-field">
          <label className="calc-page-label">Период деятельности (в днях):</label>
          <input type="number" value={period} onChange={e => setPeriod(parseInt(e.target.value, 10))} className="calc-page-input-number" />
        </div>

        <div className="calc-page-field">
          <label className="calc-page-label">Выберите валюту:</label>
          <select value={selectedCurrency} onChange={handleCurrencyChange} className="calc-page-select">
            <option value="USD">Доллар США (USD)</option>
            <option value="EUR">Евро (EUR)</option>
            <option value="RUB">Российский рубль (RUB)</option>
            <option value="BYN">Белорусский рубль (BYN)</option>
          </select>
        </div>

        <div className="calc-page-field">
          <label className="calc-page-label">Введите курсы вручную:</label>
          <div>
            <input type="number" name="USD" value={manualRates.USD} onChange={handleManualRateChange} placeholder="USD" className="calc-page-input-number" />
            <input type="number" name="EUR" value={manualRates.EUR} onChange={handleManualRateChange} placeholder="EUR" className="calc-page-input-number" />
            <input type="number" name="RUB" value={manualRates.RUB} onChange={handleManualRateChange} placeholder="RUB" className="calc-page-input-number" />
            <input type="number" name="BYN" value={manualRates.BYN} onChange={handleManualRateChange} placeholder="BYN" className="calc-page-input-number" />
          </div>
        </div>

        <div className="calc-page-field">
          <label className="calc-page-label">Актуальный курс в BYN:</label>
          <p>{`1 ${selectedCurrency} = ${exchangeRates[selectedCurrency]} (условная единица)`}</p>
        </div>

        <div className="calc-page-field">
          <h2>Продукт / Услуга</h2>
          {service ? (
            <div className="calc-page-dynamic-row">
              <input
                type="text"
                placeholder="Название услуги"
                value={service.name}
                onChange={(e) => handleServiceChange('name', e.target.value)}
                className="calc-page-input-text"
              />
              <input
                type="text"
                placeholder="Описание услуги"
                value={service.description}
                onChange={(e) => handleServiceChange('description', e.target.value)}
                className="calc-page-input-text"
              />
              <input
                type="number"
                placeholder="Количество"
                value={service.quantity}
                onChange={(e) => handleServiceChange('quantity', parseInt(e.target.value, 10))}
                className="calc-page-input-number"
              />
              <input
                type="number"
                placeholder="Стоимость"
                value={service.price}
                onChange={(e) => handleServiceChange('price', parseFloat(e.target.value))}
                className="calc-page-input-number"
              />
              <button onClick={removeService} className="calc-page-button">Удалить</button>
              <button onClick={() => submitService(service)} className="calc-page-button">Занести</button>
            </div>
          ) : (
            <button onClick={addService} className="calc-page-button">Добавить продукт / услугу</button>
          )}
        </div>

        <div className="calc-page-field">
          <h2>Материалы</h2>
          {materials.map((material, index) => (
            <div key={index} className="calc-page-dynamic-row">
              <input
                type="text"
                placeholder="Название"
                value={material.name}
                onChange={(e) => handleMaterialChange(index, 'name', e.target.value)}
                className="calc-page-input-text"
              />
              <input
                type="number"
                placeholder="Количество"
                value={material.quantity}
                onChange={(e) => handleMaterialChange(index, 'quantity', parseInt(e.target.value, 10))}
                className="calc-page-input-number"
              />
              <input
                type="number"
                placeholder="Стоимость"
                value={material.price}
                onChange={(e) => handleMaterialChange(index, 'price', parseFloat(e.target.value))}
                className="calc-page-input-number"
              />
              <button onClick={() => removeMaterial(index)} className="calc-page-button">Удалить</button>
              <button onClick={() => submitMaterial(material)} className="calc-page-button">Занести</button>
            </div>
          ))}
          <button onClick={addMaterial} className="calc-page-button">Добавить материал</button>
        </div>

        <div className="calc-page-field">
          <h2>Расходы</h2>
          {expenses.map((expense, index) => (
            <div key={index} className="calc-page-dynamic-row">
              <input
                type="text"
                placeholder="Описание"
                value={expense.description}
                onChange={(e) => handleExpenseChange(index, 'description', e.target.value)}
                className="calc-page-input-text"
              />
              <input
                type="number"
                placeholder="Сумма"
                value={expense.amount}
                onChange={(e) => handleExpenseChange(index, 'amount', parseFloat(e.target.value))}
                className="calc-page-input-number"
              />
              <button onClick={() => removeExpense(index)} className="calc-page-button">Удалить</button>
              <button onClick={() => submitExpense(expense)} className="calc-page-button">Занести</button>
            </div>
          ))}
          <button onClick={addExpense} className="calc-page-button">Добавить расход</button>
        </div>

        <div className="calc-page-field">
          <label className="calc-page-label">Прочие издержки:</label>
          <textarea value={otherExpenses} onChange={e => setOtherExpenses(e.target.value)} placeholder="Опишите издержки" className="calc-page-textarea" />
        </div>

        <div className="calc-page-field">
          <label className="calc-page-label">Выручка:</label>
          <input
            type="number"
            value={revenue}
            onChange={(e) => setRevenue(parseFloat(e.target.value))}
            className="calc-page-input-number"
          />
        </div>

        <button onClick={calculateTotalCost} className="calc-page-button">Произвести расчёт</button>
        <button onClick={submitCostCalculation} className="calc-page-button">Отправить расчет</button>

        <div className="calc-page-field">
          <label className="calc-page-label">Итоговая стоимость материалов:</label>
          <p>{totalMaterialCost.toFixed(2)} {selectedCurrency}</p>
          <label className="calc-page-label">Итоговая стоимость косвенных расходов:</label>
          <p>{totalIndirectCost.toFixed(2)} {selectedCurrency}</p>
          <label className="calc-page-label">Итоговая стоимость:</label>
          <p>{totalCost.toFixed(2)} {selectedCurrency}</p>
          <label className="calc-page-label">Итоговая прибыль:</label>
          <p>{(revenue - totalCost).toFixed(2)} {selectedCurrency}</p>
        </div>
      </div>
    </div>
  );
}

export default CalcPage;
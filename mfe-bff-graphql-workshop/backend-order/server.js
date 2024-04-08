const express = require('express');
const app = express();
const port = 3001;

app.get('/orders', (req, res) => {
  res.json([{ orderId: 1, item: 'Item 1', quantity: 1, price: 100, status: 'PENDING' }, { orderId: 2, item: 'Item 2', quantity: 2, price: 200, status: 'PENDING' }]);
});

app.listen(port, () => {
  console.log(`Order service listening at http://localhost:${port}`);
});
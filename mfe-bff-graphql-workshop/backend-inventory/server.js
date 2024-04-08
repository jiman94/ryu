const express = require('express');
const app = express();
const port = 3002;

app.get('/inventory', (req, res) => {
  res.json([{ itemId: 1, stock: 100, status: 'AVAILABLE' }, { itemId: 2, stock: 50, status: 'AVAILABLE' }]);
});

app.listen(port, () => {
  console.log(`Inventory service listening at http://localhost:${port}`);
});
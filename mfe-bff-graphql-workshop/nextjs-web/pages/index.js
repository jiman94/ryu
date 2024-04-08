import createApolloClient from '../lib/apolloClient';
import { GET_ORDERS_AND_INVENTORY } from '../graphql/queries';

export async function getServerSideProps() {
  const apolloClient = createApolloClient();

  const { data } = await apolloClient.query({
    query: GET_ORDERS_AND_INVENTORY,
  });

  return {
    props: {
      orders: data.orders,
      inventory: data.inventory,
    },
  };
}

export default function OrdersPage({ orders, inventory }) {
  return (
    <div>
      <h2>Order</h2>
      <table>
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Item</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {orders.map(({ orderId, item, quantity, price, status }) => (
            <tr key={orderId}>
              <td>{orderId}</td>
              <td>{item}</td>
              <td>{quantity}</td>
              <td>{price}</td>
              <td>{status}</td>
            </tr>
          ))}
        </tbody>
      </table>
      
      <h2>Inventory</h2>
      <table>
        <thead>
          <tr>
            <th>Item ID</th>
            <th>Stock</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {inventory.map(({ itemId, stock, status }) => (
            <tr key={itemId}>
              <td>{itemId}</td>
              <td>{stock}</td>
              <td>{status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

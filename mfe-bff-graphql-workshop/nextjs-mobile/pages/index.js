import createApolloClient from '../lib/apolloClient';
import { GET_ORDERS } from '../graphql/queries';

export async function getServerSideProps() {
  const apolloClient = createApolloClient();

  const { data } = await apolloClient.query({
    query: GET_ORDERS,
  });

  return {
    props: {
      orders: data.orders,
    },
  };
}

export default function OrdersPage({ orders }) {
  // orders props를 사용하여 주문 목록 렌더링
  return (
    <table>
      <thead>
        <tr>
          <th>Order ID</th>
          <th>Item</th>
        </tr>
      </thead>
      <tbody>
        {orders.map(({ orderId, item, quantity, price, status }) => (
          <tr key={orderId}>
            <td>{orderId}</td>
            <td>{item}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
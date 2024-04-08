import { gql } from '@apollo/client';

export const GET_ORDERS_AND_INVENTORY = gql`
  query GetOrdersAndInventory {
    orders {
      orderId
      item
      quantity
      price
      status
    }
    inventory {
      itemId
      stock
      status
    }
  }
`;
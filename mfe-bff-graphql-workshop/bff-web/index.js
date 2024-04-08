const cors = require('micro-cors')();
const { ApolloServer, gql } = require('apollo-server-micro');
const { send } = require('micro');
const fetch = require('node-fetch');

const typeDefs = gql`
type Query {
  orders: [Order]
  inventory: [InventoryItem]
}

type Order {
  orderId: Int
  item: String
  quantity: Int
  price: Float
  status: String
}

type InventoryItem {
  itemId: Int
  stock: Int
  status: String
}
`;

const resolvers = {
  Query: {
    orders: async () => {
      const response = await fetch('http://localhost:3001/orders');
      return response.json();
    },
    inventory: async () => {
      const response = await fetch('http://localhost:3002/inventory');
      return response.json();
    },
  },
};

const apolloServer = new ApolloServer({ typeDefs, resolvers });
module.exports = apolloServer.start().then(() => {
  const handler = apolloServer.createHandler({ path: '/api/graphql' });
  return cors((req, res) => req.method === 'OPTIONS' ? send(res, 200, 'ok') : handler(req, res))
});
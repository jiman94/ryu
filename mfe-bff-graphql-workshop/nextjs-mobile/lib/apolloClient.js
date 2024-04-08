import { ApolloClient, InMemoryCache, HttpLink, gql } from '@apollo/client';

const createApolloClient = () => {
  return new ApolloClient({
    ssrMode: typeof window === 'undefined', // SSR 모드 활성화 여부
    link: new HttpLink({
      uri: 'http://localhost:4000/api/graphql', // GraphQL 서버 URI
    }),
    cache: new InMemoryCache(),
  });
};

export default createApolloClient;
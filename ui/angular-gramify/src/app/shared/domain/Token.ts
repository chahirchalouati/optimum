export default interface Token {
  access_token: string;
  refresh_token: string;
  scope: string;
  id_token: string;
  token_type: string;
  expires_in: number;
}
export const TOKEN_STORE_KEY = 'TOKEN_STORE_KEY';
export const GRANT_TYPE = 'grant_type';
export const CODE = 'code';
export const REDIRECT_URI = 'redirect_uri';
export const CLIENT_ID = 'client_id';
export const CLIENT_SECRET = 'client_secret';

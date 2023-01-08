export interface User {
    username: string;
    lastName: string;
    firstName: string;
    email: string;
    password: string;
    avatar: string;
    backgroundImage: string;
}

export interface Profile {
    theme: 'black' | 'light';
    location: string;

}
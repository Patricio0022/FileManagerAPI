import './styles/main.css';
import axios from 'axios';
import { useState } from 'react';

const fetchData = async (apiUrl: any) => {
    try {
        const response = await fetch(`/api/files/txt?url=${encodeURIComponent(apiUrl)}`);
        const data = await response.text();
        console.log(data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

fetchData('https://jsonplaceholder.typicode.com/todos');





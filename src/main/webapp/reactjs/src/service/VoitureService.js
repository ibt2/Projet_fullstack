// src/main/webapp/reactjs/src/service/VoitureService.js
import axios from 'axios';

// Toujours relatif à l'origine (nginx front)
const API_BASE = process.env.REACT_APP_API_URL || '/api';

const API = {
  list: () => axios.get(`${API_BASE}/voitures`),
  get: (id) => axios.get(`${API_BASE}/voitures/${id}`),
  create: (data, proprietaireId) =>
    axios.post(`${API_BASE}/voitures${proprietaireId ? `?proprietaireId=${proprietaireId}` : ''}`, data),
  update: (id, data, proprietaireId) =>
    axios.put(`${API_BASE}/voitures/${id}${proprietaireId ? `?proprietaireId=${proprietaireId}` : ''}`, data),
  remove: (id) => axios.delete(`${API_BASE}/voitures/${id}`)
};
export default API;

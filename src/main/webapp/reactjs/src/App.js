import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavigationBar from './component/NavigationBar';
import Bienvenue from './component/Bienvenue';
import Voiture from './component/Voiture';
import VoitureListe from './component/VoitureListe';
import Footer from './component/Footer';

export default function App() {
    return (
        <div className="App" style={{ backgroundColor: '#272B30', minHeight: '100vh' }}>
            <BrowserRouter>
                <NavigationBar />
                <Routes>
                    <Route path="/" element={<Bienvenue />} />
                    <Route path="/add" element={<Voiture />} />
                    <Route path="/list" element={<VoitureListe />} />
                </Routes>
                <Footer />
            </BrowserRouter>
        </div>
    );
}

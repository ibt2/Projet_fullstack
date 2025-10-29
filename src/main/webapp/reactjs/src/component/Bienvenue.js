import React from 'react';
import { Container } from 'react-bootstrap';

export default function Bienvenue() {
    return (
        <Container className="py-5">
            <div className="p-5 mb-4 bg-dark text-white rounded-3">
                <div className="container-fluid py-5">
                    <h1 className="display-5 fw-bold">Bienvenue au Magasin des Voitures</h1>
                    <p className="col-md-8 fs-5 mt-3">
                        Le meilleur de nos voitures est exposé près de chez vous
                    </p>
                    <footer className="blockquote-footer text-white-50">Master MIOLA</footer>
                </div>
            </div>
        </Container>
    );
}

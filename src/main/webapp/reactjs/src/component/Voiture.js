import React, { useState } from 'react';
import { Card, Container, Form, Button, Row, Col } from 'react-bootstrap';
import API from '../service/VoitureService';

import MyToast from './MyToast';

const initialState = { marque: '', modele: '', couleur: '', annee: '', prix: '' };

export default function Voiture() {
    const [f, setF] = useState(initialState);
    const [toast, setToast] = useState({ show: false, bg: 'success', message: '' });

    const onChange = (e) => setF({ ...f, [e.target.name]: e.target.value });

    const onReset = () => setF(initialState);

    const submitVoiture = async (e) => {
        e.preventDefault();
        await API.create({
            marque: f.marque,
            modele: f.modele,
            couleur: f.couleur,
            annee: Number(f.annee),
            prix: Number(f.prix)
        });
        setToast({ show: true, bg: 'success', message: 'Voiture enregistrée avec succès.' });
        setTimeout(() => setToast({ ...toast, show: false }), 2000);
        setF(initialState);
    };

    return (
        <Container className="py-4">
            <MyToast show={toast.show} bg={toast.bg} message={toast.message} />
            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Ajouter Voiture</Card.Header>
                <Card.Body>
                    <Form onSubmit={submitVoiture} onReset={onReset} id="VoitureFormId">
                        <Row className="mb-3">
                            <Form.Group as={Col} controlId="formMarque">
                                <Form.Label>Marque</Form.Label>
                                <Form.Control
                                    name="marque" type="text" autoComplete="off"
                                    className="bg-dark text-white" value={f.marque} onChange={onChange} required
                                    placeholder="Entrez Marque Voiture"
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formModele">
                                <Form.Label>Modèle</Form.Label>
                                <Form.Control
                                    name="modele" type="text" autoComplete="off"
                                    className="bg-dark text-white" value={f.modele} onChange={onChange} required
                                    placeholder="Entrez Modèle Voiture"
                                />
                            </Form.Group>
                        </Row>

                        <Row className="mb-3">
                            <Form.Group as={Col} controlId="formCouleur">
                                <Form.Label>Couleur</Form.Label>
                                <Form.Control
                                    name="couleur" type="text" autoComplete="off"
                                    className="bg-dark text-white" value={f.couleur} onChange={onChange}
                                    placeholder="Couleur"
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formAnnee">
                                <Form.Label>Année</Form.Label>
                                <Form.Control
                                    name="annee" type="number" min="1900" max="2100"
                                    className="bg-dark text-white" value={f.annee} onChange={onChange}
                                    placeholder="2024"
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formPrix">
                                <Form.Label>Prix</Form.Label>
                                <Form.Control
                                    name="prix" type="number" step="0.01"
                                    className="bg-dark text-white" value={f.prix} onChange={onChange}
                                    placeholder="100000"
                                />
                            </Form.Group>
                        </Row>

                        <div className="text-end">
                            <Button type="reset" variant="secondary" size="sm" className="me-2">Reset</Button>
                            <Button type="submit" variant="success" size="sm">Submit</Button>
                        </div>
                    </Form>
                </Card.Body>
            </Card>
        </Container>
    );
}

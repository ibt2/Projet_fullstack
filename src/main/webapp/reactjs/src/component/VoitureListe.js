import React, { useEffect, useState } from 'react';
import { Card, Table, Container, Button, Modal, Form, Row, Col } from 'react-bootstrap';
import API from '../service/VoitureService';
import MyToast from './MyToast';

export default function VoitureListe() {
    const [voitures, setVoitures] = useState([]);
    const [toast, setToast] = useState({ show: false, bg: 'success', message: '' });

    // --- état pour l'édition ---
    const [showEdit, setShowEdit] = useState(false);
    const [editVoiture, setEditVoiture] = useState({
        id: null, marque: '', modele: '', couleur: '', annee: '', prix: ''
    });
    const [saving, setSaving] = useState(false);

    const load = async () => {
        const { data } = await API.list();
        setVoitures(data || []);
    };

    useEffect(() => { load(); }, []);

    const deleteVoiture = async (id) => {
        await API.remove(id);
        setVoitures(vs => vs.filter(v => v.id !== id));
        setToast({ show: true, bg: 'danger', message: 'Voiture supprimée avec succès.' });
        setTimeout(() => setToast(prev => ({ ...prev, show: false })), 2000);
    };

    // --- ouvrir la modale avec les valeurs de la voiture courant ---
    const openEdit = (v) => {
        setEditVoiture({
            id: v.id,
            marque: v.marque ?? '',
            modele: v.modele ?? '',
            couleur: v.couleur ?? '',
            annee: v.annee ?? '',
            prix: v.prix ?? ''
        });
        setShowEdit(true);
    };

    const closeEdit = () => {
        setShowEdit(false);
        setEditVoiture({ id: null, marque: '', modele: '', couleur: '', annee: '', prix: '' });
    };

    const onEditChange = (e) => {
        const { name, value } = e.target;
        setEditVoiture(v => ({ ...v, [name]: value }));
    };

    const saveEdit = async (e) => {
        e.preventDefault();
        if (!editVoiture.id) return;

        // Optionnel: si tu as un proprietaireId à lier :
        // const proprietaireId = 1; // sinon laisse undefined
        const proprietaireId = undefined;

        const payload = {
            marque: editVoiture.marque,
            modele: editVoiture.modele,
            couleur: editVoiture.couleur,
            // s'assurer des types numériques si ton backend attend number
            annee: editVoiture.annee !== '' ? Number(editVoiture.annee) : null,
            prix: editVoiture.prix !== '' ? Number(editVoiture.prix) : null
        };

        try {
            setSaving(true);
            const { data: updated } = await API.update(editVoiture.id, payload, proprietaireId);

            // mettre à jour la liste localement
            setVoitures(prev => prev.map(v => (v.id === updated.id ? updated : v)));

            setToast({ show: true, bg: 'success', message: 'Voiture modifiée avec succès.' });
            setTimeout(() => setToast(prev => ({ ...prev, show: false })), 2000);
            closeEdit();
        } catch (err) {
            console.error(err);
            setToast({ show: true, bg: 'danger', message: "Échec de la modification." });
            setTimeout(() => setToast(prev => ({ ...prev, show: false })), 2500);
        } finally {
            setSaving(false);
        }
    };

    return (
        <Container className="py-4">
            <MyToast show={toast.show} bg={toast.bg} message={toast.message} />

            <Card className="border border-dark bg-dark text-white">
                <Card.Header>Liste Voitures</Card.Header>
                <Card.Body className="bg-dark">
                    <Table bordered hover striped variant="dark" responsive>
                        <thead>
                        <tr>
                            <th>Marque</th><th>Modèle</th><th>Couleur</th><th>Année</th><th>Prix</th><th className="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {voitures.length === 0 ? (
                            <tr><td colSpan="6" className="text-center">Aucune Voiture disponible</td></tr>
                        ) : (
                            voitures.map(v => (
                                <tr key={v.id}>
                                    <td>{v.marque}</td>
                                    <td>{v.modele}</td>
                                    <td>{v.couleur}</td>
                                    <td>{v.annee}</td>
                                    <td>{v.prix}</td>
                                    <td className="text-center d-flex gap-2 justify-content-center">
                                        <Button size="sm" variant="outline-info" onClick={() => openEdit(v)}>
                                            Modifier
                                        </Button>
                                        <Button size="sm" variant="outline-danger" onClick={() => deleteVoiture(v.id)}>
                                            Supprimer
                                        </Button>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>


            <Modal show={showEdit} onHide={closeEdit} centered>
                <Form onSubmit={saveEdit}>
                    <Modal.Header closeButton>
                        <Modal.Title>Modifier la voiture</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Row className="g-3">
                            <Col md={6}>
                                <Form.Group>
                                    <Form.Label>Marque</Form.Label>
                                    <Form.Control
                                        name="marque"
                                        value={editVoiture.marque}
                                        onChange={onEditChange}
                                        required
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={6}>
                                <Form.Group>
                                    <Form.Label>Modèle</Form.Label>
                                    <Form.Control
                                        name="modele"
                                        value={editVoiture.modele}
                                        onChange={onEditChange}
                                        required
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={6}>
                                <Form.Group>
                                    <Form.Label>Couleur</Form.Label>
                                    <Form.Control
                                        name="couleur"
                                        value={editVoiture.couleur}
                                        onChange={onEditChange}
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={3}>
                                <Form.Group>
                                    <Form.Label>Année</Form.Label>
                                    <Form.Control
                                        type="number"
                                        name="annee"
                                        value={editVoiture.annee}
                                        onChange={onEditChange}
                                        min="1900"
                                        max="2100"
                                    />
                                </Form.Group>
                            </Col>
                            <Col md={3}>
                                <Form.Group>
                                    <Form.Label>Prix</Form.Label>
                                    <Form.Control
                                        type="number"
                                        step="0.01"
                                        name="prix"
                                        value={editVoiture.prix}
                                        onChange={onEditChange}
                                        min="0"
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={closeEdit}>Annuler</Button>
                        <Button type="submit" variant="primary" disabled={saving}>
                            {saving ? 'Enregistrement…' : 'Enregistrer'}
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </Container>
    );
}

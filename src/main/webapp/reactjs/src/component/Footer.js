import React from 'react';
import { Navbar, Container } from 'react-bootstrap';

export default function Footer() {
    const fullYear = new Date().getFullYear();
    return (
        <Navbar fixed="bottom" bg="dark" variant="dark">
            <Container className="justify-content-center text-muted">
                {fullYear}-{fullYear + 1}, All Rights Reserved by Master MIOLA
            </Container>
        </Navbar>
    );
}

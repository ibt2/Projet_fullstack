import React from 'react';
import { Toast, ToastContainer } from 'react-bootstrap';

export default function MyToast({ show, bg = 'success', message }) {
    return (
        <ToastContainer position="top-center" className="mt-3">
            <Toast show={show} bg={bg}>
                <Toast.Body className="text-white">{message}</Toast.Body>
            </Toast>
        </ToastContainer>
    );
}

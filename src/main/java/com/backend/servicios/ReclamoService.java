package com.backend.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.backend.entidades.Reclamo;
import com.backend.repositorios.ReclamoRepository;


@Service
public class ReclamoService {

	private final JavaMailSender javaMailSender;
	private final ReclamoRepository reclamoRepository;

	@Autowired
	public ReclamoService(JavaMailSender javaMailSender, ReclamoRepository reclamoRepository) {
	    this.javaMailSender = javaMailSender;
	    this.reclamoRepository = reclamoRepository;
	}

	public void enviarCorreoDisculpas(String destinatario, String asunto, String contenido) {
	    SimpleMailMessage correo = new SimpleMailMessage();
	    correo.setTo(destinatario);
	    correo.setSubject(asunto);
	    correo.setText(contenido);

	    javaMailSender.send(correo);
	}

	public Reclamo obtenerReclamoPorId(Long id) {
	    return reclamoRepository.findById(id).orElse(null);
	}

    public void actualizarReclamo(Reclamo reclamo) {

        reclamoRepository.save(reclamo);
    }
}

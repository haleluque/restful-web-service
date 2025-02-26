package com.haleluque.rest.restful_web_services.socialMedia.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String details) {}

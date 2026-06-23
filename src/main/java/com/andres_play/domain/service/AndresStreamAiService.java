package com.andres_play.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AndresStreamAiService {
    @UserMessage("Actua como un experto en plataforma de streaming dandome un saludo personalizado maximo de 120 caracteres, mi plataforma se llama {{plataform}}")
    String generateGreetings(String plataform);

    @SystemMessage("Eres un experto en cine que recomienda películas personalizadas según los gustos del ususario.Debes recomendar máximo 3 películas.No incluyas películas que estén por fuera de la plataforma AndresStream ")
    String generateMoviesSuggestion(@UserMessage String userMessage);
}

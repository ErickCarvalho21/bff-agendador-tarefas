package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeingError implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response){

        String mensagemErro = mensagemErro(response);


        switch (response.status()){
            case 409:
                return new ConflictException("Error: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Error: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Error: " + mensagemErro);
            case 400:
                return  new ILLegalArgumentsException("Error: " + mensagemErro);
            default:
                return new BusinessException("Error: " + mensagemErro);
        }
    }

    private String mensagemErro(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

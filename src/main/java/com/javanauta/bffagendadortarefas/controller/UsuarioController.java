package com.javanauta.bffagendadortarefas.controller;


import com.javanauta.bffagendadortarefas.business.UsuarioService;
import com.javanauta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadrasto e Login e usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuario", description = "criar um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadrastado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }
    @PostMapping("/login")
    @Operation(summary = "Login Usuario", description = "login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {
        return usuarioService.LoginUsuario(usuarioDTO);
    }
    @GetMapping
    @Operation(summary = "Buscar dados de Usuario por Email",
            description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario nao cadrastado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email")String email,
                                                                   @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuario por Id", description = "Deletar usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmai(@PathVariable String email,
                                                     @RequestHeader(name ="Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();

    }
    @PutMapping
    @Operation(summary = "Atualizar Dados De Usuario",
            description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao cadrastado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuarios", description = "Atualiza Endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endreço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco (@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                                                 @RequestHeader(name ="Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuarios", description = "Atualiza Telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone (@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
                                                                 @RequestHeader(name ="Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuarios", description = "Salva Endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endreço Salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario naop encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadrastaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadrastaEndereco(token, dto));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuarios", description = "Salva Telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone Salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario naop encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadrastaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadrastaTelefone(token, dto));
    }


}

package usm.hdd.Taller1.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usm.hdd.Taller1.entities.Cliente;
import usm.hdd.Taller1.services.ClientesService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RestController
public class ClientesController {
    @Autowired
    private ClientesService clientesService;
    public static Boolean validaRut ( String rut ) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
    }

    /**
     * Valida el dígito verificador
     */
    public static String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }
    public boolean validaEstado(String est){
        String [] estados = {"Habilitado","Con Deuda","En lista negra"};
        return Stream.of(estados).anyMatch(t->t.equalsIgnoreCase(est));
    }
    @PostMapping("/ingresarCliente")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        if (!validaRut(cliente.getRut()) || cliente.getApellidos().isEmpty() || cliente.getNombre().isEmpty() || !validaEstado(cliente.getEstado())){
            return ResponseEntity.badRequest().build();
        }try{
            Cliente cliente1 = clientesService.crear(cliente);
            return ResponseEntity.ok(cliente1);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/mostrarClientes")
    public ResponseEntity<List<Cliente>> listar(){
        try{
            return ResponseEntity.ok(this.clientesService.listar());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/mostrarClientes/{estado}")
    public ResponseEntity<List<Cliente>> filtrar(@PathVariable String estado){
        if (!validaEstado(estado)){
            return ResponseEntity.badRequest().build();
        }
        try{
            return ResponseEntity.ok(this.clientesService.filtrar(estado));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}

package com.mericar.repository;

import com.mericar.entity.Usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {
        //LOGIN
        Optional<Usuario> findByUsuario(String usuario);
        //Validaciones
        boolean existsByUsuario(String usuario);
        boolean existsByCorreo(String correo);  
        //LIstar usuarios 
        List<Usuario> findAllByOrderByNombresAsc();
        //cambiar estado
        @Modifying
        @Query(
            value = """
                UPDATE usuarios
                SET activo = :activo,
                    fecha_actualizacion = CURRENT_TIMESTAMP
                WHERE id_usuario = :idUsuario
            """,
            nativeQuery = true
        )
        int actualizarEstado(
            @Param("idUsuario") Long idUsuario,
            @Param("activo") Boolean activo
        );  
        }
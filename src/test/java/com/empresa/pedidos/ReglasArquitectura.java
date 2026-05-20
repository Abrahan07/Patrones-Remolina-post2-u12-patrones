package com.empresa.pedidos;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.empresa.pedidos")
public class ReglasArquitectura {

    // Regla 1: El dominio no depende de infraestructura ni adaptadores
    @ArchTest
    static final ArchRule dominioAislado = noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                    "..infraestructura..", "..adaptadores..",
                    "javax.persistence..", "org.springframework.mail..");

    // Regla 2: Los controladores solo dependen de la Facade
    @ArchTest
    static final ArchRule controladorSoloFacade = classes()
            .that().resideInAPackage("..adaptadores.rest..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                    "..adaptadores.rest..",
                    "..adaptadores.facade..",
                    "..dominio..",
                    "org.springframework.web..",
                    "org.springframework.http..",
                    "org.springframework.stereotype..",
                    "java..");

    // Regla 3: Los puertos de dominio son interfaces
    @ArchTest
    static final ArchRule puertosComoInterfaces = classes()
            .that().resideInAPackage("..dominio.puertos..")
            .should().beInterfaces();

    // Regla 4: Los procesadores implementan ProcesadorPedido
    @ArchTest
    static final ArchRule procesadoresImplementanPuerto = classes()
            .that().resideInAPackage("..adaptadores.procesadores..")
            .should().implement(ProcesadorPedido.class);

    // Regla 5: La infraestructura no accede a los adaptadores REST
    @ArchTest
    static final ArchRule infraNoAccedeRest = noClasses()
            .that().resideInAPackage("..infraestructura..")
            .should().accessClassesThat()
            .resideInAPackage("..adaptadores.rest..");
}
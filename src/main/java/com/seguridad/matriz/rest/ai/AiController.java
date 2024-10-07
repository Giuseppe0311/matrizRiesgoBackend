package com.seguridad.matriz.rest.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ia")
@PreAuthorize("hasAnyRole('user_client')")
public class AiController {

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Para procesar JSON

    @GetMapping
    public ResponseAiDTO getResponse(@RequestParam String event,
                                     @RequestParam String riskLevel,
                                     @RequestParam String probability,
                                     @RequestParam String impact,
                                     @RequestParam int value) throws JsonProcessingException {

        String validItems = """
    {
        "Dominios": [
            {
                "Dominio": "Políticas de Seguridad",
                "Objetivos": [
                    {
                        "Objetivo": "Directrices de la Dirección en seguridad de la información",
                        "Controles": [
                            "Conjunto de políticas para la seguridad de la información",
                            "Revisión de las políticas para la seguridad de la información"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Aspectos Organizativos de la Seguridad de la Información",
                "Objetivos": [
                    {
                        "Objetivo": "Organización interna",
                        "Controles": [
                            "Asignación de responsabilidades para la seguridad de la información",
                            "Segregación de tareas",
                            "Contacto con las autoridades",
                            "Contacto con grupos de interés especial",
                            "Seguridad de la información en la gestión de proyectos"
                        ]
                    },
                    {
                        "Objetivo": "Dispositivos para movilidad y teletrabajo",
                        "Controles": [
                            "Política de uso de dispositivos para movilidad",
                            "Teletrabajo"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Seguridad Ligada a los Recursos Humanos",
                "Objetivos": [
                    {
                        "Objetivo": "Antes de la contratación",
                        "Controles": [
                            "Investigación de antecedentes",
                            "Términos y condiciones de contratación"
                        ]
                    },
                    {
                        "Objetivo": "Durante la contratación",
                        "Controles": [
                            "Responsabilidades de gestión",
                            "Concienciación, educación y capacitación en seguridad de la información",
                            "Proceso disciplinario"
                        ]
                    },
                    {
                        "Objetivo": "Cese o cambio de puesto de trabajo",
                        "Controles": [
                            "Cese o cambio de puesto de trabajo"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Gestión de Activos",
                "Objetivos": [
                    {
                        "Objetivo": "Responsabilidad sobre los activos",
                        "Controles": [
                            "Inventario de activos",
                            "Propiedad de los activos",
                            "Uso aceptable de los activos",
                            "Devolución de activos"
                        ]
                    },
                    {
                        "Objetivo": "Clasificación de la información",
                        "Controles": [
                            "Directrices de clasificación",
                            "Etiquetado y manipulado de la información",
                            "Manipulación de activos"
                        ]
                    },
                    {
                        "Objetivo": "Manejo de los soportes de almacenamiento",
                        "Controles": [
                            "Gestión de soportes extraíbles",
                            "Eliminación de soportes",
                            "Soportes físicos en tránsito"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Control de Accesos",
                "Objetivos": [
                    {
                        "Objetivo": "Requisitos de negocio para el control de accesos",
                        "Controles": [
                            "Política de control de accesos",
                            "Control de acceso a las redes y servicios asociados"
                        ]
                    },
                    {
                        "Objetivo": "Gestión de acceso de usuario",
                        "Controles": [
                            "Gestión de altas/bajas en el registro de usuarios",
                            "Gestión de los derechos de acceso asignados a usuarios",
                            "Gestión de los derechos de acceso con privilegios especiales",
                            "Gestión de información confidencial de autenticación de usuarios",
                            "Revisión de los derechos de acceso de los usuarios",
                            "Retirada o adaptación de los derechos de acceso"
                        ]
                    },
                    {
                        "Objetivo": "Responsabilidades del usuario",
                        "Controles": [
                            "Uso de información confidencial para la autenticación"
                        ]
                    },
                    {
                        "Objetivo": "Control de acceso a sistemas y aplicaciones",
                        "Controles": [
                            "Restricción del acceso a la información",
                            "Procedimientos seguros de inicio de sesión",
                            "Gestión de contraseñas de usuario",
                            "Uso de herramientas de administración de sistemas",
                            "Control de acceso al código fuente de los programas"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Cifrado",
                "Objetivos": [
                    {
                        "Objetivo": "Controles criptográficos",
                        "Controles": [
                            "Política de uso de los controles criptográficos",
                            "Gestión de claves"
                        ]
                    }
                ]
            },
            {
                "Dominio": "Seguridad Física y Ambiental",
                "Objetivos": [
                    {
                        "Objetivo": "Áreas seguras",
                        "Controles": [
                            "Perímetro de seguridad física",
                            "Controles físicos de entrada",
                            "Seguridad de oficinas, despachos y recursos",
                            "Protección contra las amenazas externas y ambientales",
                            "El trabajo en áreas seguras",
                            "Áreas de acceso público, carga y descarga"
                        ]
                    },
                    {
                        "Objetivo": "Seguridad de los equipos",
                        "Controles": [
                            "Emplazamiento y protección de equipos",
                            "Instalaciones de suministro",
                            "Seguridad del cableado",
                            "Mantenimiento de los equipos",
                            "Salida de activos fuera de las dependencias de la empresa",
                            "Seguridad de los equipos y activos fuera de las instalaciones",
                            "Reutilización o retirada segura de dispositivos de almacenamiento",
                            "Equipo informático de usuario desatendido",
                            "Política de puesto de trabajo despejado y bloqueo de pantalla"
                        ]
                    }
                ]
            }
        ]
    }
    """;

        // Contexto para dar más información a la IA
        String context = """
        En la norma ISO/IEC 27001, un dominio puede tener varios objetivos, y un objetivo puede tener varios controles asociados.

        Aquí está la lista de Dominios, Objetivos y Controles permitidos en formato JSON, con etiquetas explícitas:

        """ + validItems + """

        **Importante:** Los "Controles" están jerárquicamente relacionados con sus "Objetivos", y los "Objetivos" están bajo sus respectivos "Dominios". Usa esta estructura para asociar correctamente los controles y objetivos al dominio correspondiente.
        """;

        // Plantilla de prompt para hacer la pregunta a la IA
        String promptTemplate = """
        Analiza el siguiente escenario de riesgo:
        - Evento: %s
        - Nivel de riesgo: %s
        - Probabilidad: %s
        - Impacto: %s
        - Valor: %d

        Proporciona **exclusivamente** una respuesta en el siguiente formato JSON exacto **sin añadir ningún texto adicional**:
        {
            "analisis": "Breve descripción del análisis en una sola frase",
            "dominio": "Dominio de ISO 27001 aplicable o 'No relevante' si el evento no es válido",
            "objetivo": "Objetivo específico del dominio o 'No aplicable' si el evento no es válido",
            "control": "Control específico recomendado o 'No aplicable' si el evento no es válido"
        }

        Restricciones:
        1. La respuesta **debe ser solo JSON** y no debe contener texto adicional.
        2. Mantén el análisis conciso, máximo una frase.
        3. Usa **únicamente** los "Dominios", "Objetivos" y "Controles" proporcionados en la lista anterior, respetando su jerarquía. No utilices otros que no estén en la lista.
        4. Si el evento no tiene sentido en el contexto de seguridad, usa los valores predeterminados mencionados.
        5. La respuesta debe ser exactamente en el formato JSON especificado.
        """;

        // Combinación del contexto y los parámetros
        String fullPrompt = context + String.format(promptTemplate, event, riskLevel, probability, impact, value);

        // Llamada al modelo AI
        String aiResponse = chatModel.call(fullPrompt);

        // Convierte la respuesta de la IA a un objeto ResponseAiDTO
        return objectMapper.readValue(aiResponse, ResponseAiDTO.class);
    }

}


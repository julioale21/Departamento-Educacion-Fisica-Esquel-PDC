package com.example.departamentoeducacionfisicaesquel.Utils;

import java.security.PublicKey;

public class Utils {

    public class ERRORS{
        public static final String REQUESTMAILERRORMESSAGE = "Algo ocurrio y no se pudo realizar la solicitud. Intenta nuevamente";

        public class FORMS{
            public static final String EMPTYFIRSTNAME = "Debe ingresar el nombre";
            public static final String EMPTYLASTNAME = "Debe ingresar el apellido";
            public static final String EMPTYHIERARCHY = "Debe ingresar su jerarquía";
            public static final String EMPTYDEPENDENCY = "Debe ingresar su dependencia";
            public static final String EMPTYEMAILADDRESS = "Debe ingresar su dirección de correo";

        }
    }

    public class EMAIL{

        public static final String ADDRESS="depeducacionfisicaesquel@gmail.com";
        public static final String ADDRESS2="julioale04031981@gmail.com";
        public static final String PASSWORD = "zcgf whuc kutq peza";
        public static final String SUBJECT = "Solicitud de usuario y contraseña";
        public static final String RESPONSEMESSAGE = "Te enviaremos los datos de tu cuenta al mail que ingresaste";
        public static final String REQUESTMESSAGE = "Solicito se me envie un usuario y contraseña para acceder a la aplicación de Educación Física.";

        public class CONTENTMESSAGE{
            public static final String NAME ="Nombre";
            public static final String HIERARCHY ="Jerarquia";
            public static final String DEPENDENCY ="Dependencia";
            public static final String EMAILADDRESS ="Email";
        }
    }



    public class CLASSIFICATIONS{
        public static final String CATEGORY = "category";
        public static final String EXERCISES = "Ejercicios";
        public static final String PRINCIPAL="dashboard";
        public static final String STRETCHING = "estiramientos";
        public static final String PHYSICALEXERCISES = "ejercicios fisicos";
    }

    public class ENTITY{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SHORTDESCRIPTION = "shortDescription";
        public static final String DESCRIPTION = "description";
        public static final String MUSCLEGROUP = "muscleGroup";
        public static final String IMAGEURL = "imageURL";
        public static final String CATEGORY = "category";
    }

}

/*package org.cvarela.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.cvarela.service.ServiceMongoException;
import java.util.logging.Logger;

@TransactionalMongo
@Interceptor
public class TransactionalMongoInterceptor {


    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        try {
            log.info(" ------> iniciando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());


            Object resultado = invocation.proceed();


            log.info(" ------> realizando commit y finalizando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());
            return resultado;
        } catch (ServiceMongoException e){

            throw e;
        }
    }
}*/

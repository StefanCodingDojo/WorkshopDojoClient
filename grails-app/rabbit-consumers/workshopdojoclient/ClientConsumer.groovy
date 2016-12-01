package workshopdojoclient

import com.budjb.rabbitmq.consumer.MessageContext

class ClientConsumer {

    ClientService clientService

    private static final String SERVICE_NOT_FOUND
    private static final String NO_SUCH_METHOD

    /**
     * Consumer configuration.
     */
    static rabbitConfig = [
        queue: "client"
    ]

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body    The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    def handleMessage(def body, MessageContext context) {
        def data

        if (body.method.equals("getAll")) {
            data = getData {
                clientService.getAll()
            }
        } else if (body.method.equals("getById")) {
            data = getData {
                clientService.getById(body.id as long)
            }
        } else if (body.method.equals("save")) {
            data = getData {
                clientService.save(body.client)
            }
        } else if (body.method.equals("delete")) {
            data = getData {
                clientService.delete(body.id as long)
            }
        } else {
            data = NO_SUCH_METHOD
        }

        data
    }

    private def getData (Closure c) {
        def data

        try {
            data = c.call()
        } catch (Exception ignore) {
            data = SERVICE_NOT_FOUND
        }
    }
}

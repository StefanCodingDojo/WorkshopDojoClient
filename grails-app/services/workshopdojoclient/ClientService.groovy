package workshopdojoclient

class ClientService {

    private static final String ERROR = "error"
    private static final String NO_CLIENTS = "no clients"

    def getAll() {
        def clients  = User.findAll()
        def payload = []

        clients.each {
            payload << marshallClient(it)
        }

        if (payload.size() == 0) {
            payload = [NO_CLIENTS]
        }

        payload
    }

    def getById (long id) {
        User client = User.findById(id)
        marshallClient(client)
    }

    def save (def client) {
        User clientToSave

        if (client.id != null) {
            clientToSave = User.findById(client.id)
        } else {
            clientToSave = new User()
        }

        clientToSave.vorname = client.vorname
        clientToSave.nachname = client.nachname
        clientToSave.firma = client.firma
        clientToSave.title = client.title

        if (clientToSave.validate()) {
            clientToSave.save(flush: true, failOnError: true)
            marshallClient(clientToSave)
        }
    }

    def delete(long id) {
        User clientToDelete = User.findById(id)

        if (clientToDelete != null) {
            try {
                clientToDelete.delete(flush: true)
            } catch (Exception ex) {
                log.info("User konnte nicht gelöscht werden!")
                ERROR
            }
        }
        "success"
    }

    def marshallClient (def client) {
        def payload = [
                id: client.id,
                vorname: client.vorname,
                nachname: client.nachname,
                firma: client.firma,
                title: client.title
        ]

        payload
    }
}

import workshopdojoclient.User
import workshopdojoclient.User

class BootStrap {

    def init = { servletContext ->
        User.init()
    }
    def destroy = {
    }
}

package workshopdojoclient

class User {

    String vorname
    String nachname
    String firma
    String title

    static constraints = {
    }

    static void init () {
        if (count() == 0) {
            new User(vorname: "Stefan", nachname: "Rühl", firma: "Lineas", title: "Entwickler").save(flush: true)
            new User(vorname: "Dieter", nachname: "Nuhr", firma: "VW", title: "Teamleiter").save(flush: true)
            new User(vorname: "Detlef", nachname: "Grunt", firma: "IT Systems", title: "Tester").save(flush: true)
        }
    }
}

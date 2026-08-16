package university

class HelloController {

    def index() {
    }

    def showMessage(String name) {
        render "Hello, ${name ?: 'Guest'}!"
    }
}
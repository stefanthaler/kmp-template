import net.thalerit.Hello

import react.VFC
import react.create
import react.dom.client.createRoot

import react.dom.html.ReactHTML.div
import web.dom.document

private val Application = VFC {
    div {
        +"TEST"

        +Hello
    }
}

private fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    createRoot(container)
        .render(Application.create())
}

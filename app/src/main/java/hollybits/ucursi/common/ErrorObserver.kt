package hollybits.ucursi.common

import androidx.lifecycle.Observer

class ErrorObserver(private val handler:ErrorDisplayer)  : Observer<String>{

    interface ErrorDisplayer{
        fun displayError(reason:String)
    }

    override fun onChanged(t: String?) {
        t?.let { error->
            handler.displayError(error)
        }
    }
}
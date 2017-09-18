package megaleios.com.myseeds.Domains.Main.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import megaleios.com.myseeds.R

/**
 * Created by ulyssesboumann on 16/08/17.
 */

class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_account, container, false)

        return view
    }
}

package megaleios.com.myseeds.Domains.Main.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import megaleios.com.myseeds.Domains.ChangePassword.Activity.ChangePasswordActivity
import megaleios.com.myseeds.Domains.Help.Activity.HelpActivity
import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity
import megaleios.com.myseeds.Domains.Main.Activity.MainActivity
import megaleios.com.myseeds.Domains.MyCards.Activity.MyCardsActivity
import megaleios.com.myseeds.Domains.Profile.ProfileActivity.ProfileActivity
import megaleios.com.myseeds.Domains.Terms.Activity.TermsActivity
import megaleios.com.myseeds.Models.Auth

import megaleios.com.myseeds.R
import megaleios.com.myseeds.Util.SessionManager

/**
 * Created by ulyssesboumann on 16/08/17.
 */

class AccountFragment : Fragment() {
    private lateinit var profileCard: CardView
    private lateinit var card_change_password: CardView
    private lateinit var card_help: CardView
    private lateinit var card_terms: CardView
    private lateinit var card_my_cards: CardView
    private lateinit var card_exit: CardView
    private lateinit var imageView4: ImageView

    private var sessionManager: SessionManager? = null

    private lateinit var textView6: TextView
    private lateinit var user_email: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_account, container, false)
        profileCard = view.findViewById(R.id.profile_card) as CardView
        card_change_password = view.findViewById(R.id.card_change_password) as CardView
        card_help = view.findViewById(R.id.card_help) as CardView
        card_terms = view.findViewById(R.id.card_terms) as CardView
        card_my_cards = view.findViewById(R.id.card_my_cards) as CardView
        card_exit = view.findViewById(R.id.card_exit) as CardView
        imageView4 = view.findViewById(R.id.imageView4) as ImageView
        textView6 = view.findViewById(R.id.textView6) as TextView
        user_email = view.findViewById(R.id.user_email) as TextView

        sessionManager = SessionManager(context)
        if(!sessionManager!!.getUsuario().getFacebookid().equals("")){
              card_change_password.visibility = View.GONE
          }
        textView6.setText(sessionManager!!.getUsuario().getFullName())
        user_email.setText(sessionManager!!.getUsuario().getEmail())
        Glide.with(context).load(sessionManager!!.getUsuario().getPhoto()).into(imageView4)

        profileCard.setOnClickListener {
            // Handler code here.
            val i = Intent(activity, ProfileActivity::class.java)
            startActivity(i)
        }
        card_change_password.setOnClickListener {
            // Handler code here.
            val i = Intent(activity, ChangePasswordActivity::class.java)
            startActivity(i)
        }
        card_help.setOnClickListener {
            // Handler code here.
            val i = Intent(activity, HelpActivity::class.java)
            startActivity(i)
        }
        card_terms.setOnClickListener {
            // Handler code here.
            val i = Intent(activity, TermsActivity::class.java)
            startActivity(i)
        }
        card_my_cards.setOnClickListener {
            // Handler code here.
            val i = Intent(activity, MyCardsActivity::class.java)
            startActivity(i)
        }
        card_exit.setOnClickListener {
            // Handler code here.
            var sessionManager = SessionManager(context)
            sessionManager.logoutUser();
            val i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }

        return view
    }

    override fun onResume() {
        val sessionManager = SessionManager(context)
        Glide.with(context).load(sessionManager!!.getUsuario().getPhoto()).into(imageView4)
        super.onResume()
    }


}

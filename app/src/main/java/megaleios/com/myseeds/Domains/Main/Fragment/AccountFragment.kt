package megaleios.com.myseeds.Domains.Main.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import megaleios.com.myseeds.Domains.ChangePassword.Activity.ChangePasswordActivity
import megaleios.com.myseeds.Domains.Help.Activity.HelpActivity
import megaleios.com.myseeds.Domains.Instituicao.Activity.InstituicaoActivity
import megaleios.com.myseeds.Domains.MyCards.Activity.MyCardsActivity
import megaleios.com.myseeds.Domains.Profile.ProfileActivity.ProfileActivity
import megaleios.com.myseeds.Domains.Terms.Activity.TermsActivity

import megaleios.com.myseeds.R

/**
 * Created by ulyssesboumann on 16/08/17.
 */

class AccountFragment : Fragment() {
    private lateinit var profileCard: CardView
    private lateinit var card_change_password: CardView
    private lateinit var card_help: CardView
    private lateinit var card_terms: CardView
    private lateinit var card_my_cards: CardView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_account, container, false)
        profileCard = view.findViewById(R.id.profile_card) as CardView
        card_change_password = view.findViewById(R.id.card_change_password) as CardView
        card_help = view.findViewById(R.id.card_help) as CardView
        card_terms = view.findViewById(R.id.card_terms) as CardView
        card_my_cards = view.findViewById(R.id.card_my_cards) as CardView

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

        return view
    }
}

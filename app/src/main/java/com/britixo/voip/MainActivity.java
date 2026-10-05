package com.britixo.voip;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int blue = Color.rgb(36, 87, 214);
    private final int text = Color.rgb(15, 23, 42);
    private final int muted = Color.rgb(100, 116, 139);

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        showLogin();
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private TextView label(String value, int size, int color, boolean bold) {
        TextView v = new TextView(this);
        v.setText(value);
        v.setTextSize(size);
        v.setTextColor(color);
        if (bold) v.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return v;
    }

    private void showLogin() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(248, 250, 252));

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(24), dp(46), dp(24), dp(30));
        page.setGravity(Gravity.CENTER_HORIZONTAL);
        scroll.addView(page, new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView mark = label("B", 28, Color.WHITE, true);
        mark.setGravity(Gravity.CENTER);
        mark.setBackgroundResource(R.drawable.primary_button_bg);
        page.addView(mark, new LinearLayout.LayoutParams(dp(58), dp(58)));

        TextView brand = label("BRITIXO", 27, text, true);
        brand.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams brandLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        brandLp.topMargin = dp(14);
        page.addView(brand, brandLp);

        TextView title = label("VoIP", 18, muted, false);
        title.setGravity(Gravity.CENTER);
        page.addView(title);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(20), dp(20), dp(20), dp(20));
        card.setBackgroundResource(R.drawable.card_bg);
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardLp.topMargin = dp(34);
        page.addView(card, cardLp);

        TextView heading = label("Sign in to Britixo", 22, text, true);
        card.addView(heading);
        TextView intro = label("Use your existing Britixo account. Your workspace, role and extension will be resolved securely by the mobile service.", 14, muted, false);
        LinearLayout.LayoutParams introLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        introLp.topMargin = dp(8);
        introLp.bottomMargin = dp(18);
        card.addView(intro, introLp);

        EditText email = new EditText(this);
        email.setHint("Email or username");
        email.setSingleLine(true);
        email.setTextSize(15);
        email.setTextColor(text);
        email.setHintTextColor(muted);
        email.setBackgroundResource(R.drawable.field_bg);
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        card.addView(email, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54)));

        EditText password = new EditText(this);
        password.setHint("Password");
        password.setSingleLine(true);
        password.setTextSize(15);
        password.setTextColor(text);
        password.setHintTextColor(muted);
        password.setBackgroundResource(R.drawable.field_bg);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LinearLayout.LayoutParams passLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
        passLp.topMargin = dp(12);
        card.addView(password, passLp);

        Button signIn = new Button(this);
        signIn.setText("Sign in");
        signIn.setTextColor(Color.WHITE);
        signIn.setTextSize(15);
        signIn.setAllCaps(false);
        signIn.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        signIn.setBackgroundResource(R.drawable.primary_button_bg);
        LinearLayout.LayoutParams signLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
        signLp.topMargin = dp(18);
        card.addView(signIn, signLp);
        signIn.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("Mobile service not enabled yet")
            .setMessage("This APK is the direct-install UI/test build. The secure Britixo mobile login, device session and FCM bridge must be enabled on the server before real account login and background VoIP calling are activated.")
            .setPositiveButton("Preview dialler", (d, w) -> showDialler())
            .setNegativeButton("Close", null)
            .show());

        Button preview = new Button(this);
        preview.setText("Preview dialler");
        preview.setTextSize(14);
        preview.setAllCaps(false);
        preview.setTextColor(blue);
        preview.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams pLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(48));
        pLp.topMargin = dp(8);
        card.addView(preview, pLp);
        preview.setOnClickListener(v -> showDialler());

        TextView status = label("TEST BUILD • package com.britixo.voip.test", 11, muted, false);
        status.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams statusLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        statusLp.topMargin = dp(26);
        page.addView(status, statusLp);

        setContentView(scroll);
    }

    private void showDialler() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(24), dp(18), dp(18));
        root.setBackgroundColor(Color.rgb(248, 250, 252));

        LinearLayout top = new LinearLayout(this);
        top.setGravity(Gravity.CENTER_VERTICAL);
        TextView identity = label("Britixo VoIP", 21, text, true);
        top.addView(identity, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        TextView state = label("● Preview", 12, muted, true);
        top.addView(state);
        root.addView(top);

        TextView ext = label("Extension —", 13, muted, false);
        LinearLayout.LayoutParams extLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        extLp.topMargin = dp(4);
        extLp.bottomMargin = dp(22);
        root.addView(ext, extLp);

        EditText number = new EditText(this);
        number.setHint("Enter extension or number");
        number.setGravity(Gravity.CENTER);
        number.setTextSize(24);
        number.setTextColor(text);
        number.setHintTextColor(muted);
        number.setBackgroundResource(R.drawable.field_bg);
        number.setInputType(InputType.TYPE_CLASS_PHONE);
        root.addView(number, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(62)));

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(3);
        grid.setRowCount(4);
        String[] keys = {"1","2\nABC","3\nDEF","4\nGHI","5\nJKL","6\nMNO","7\nPQRS","8\nTUV","9\nWXYZ","*","0\n+","#"};
        LinearLayout.LayoutParams gridLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        gridLp.topMargin = dp(18);
        root.addView(grid, gridLp);
        for (String k : keys) {
            Button b = new Button(this);
            b.setText(k);
            b.setAllCaps(false);
            b.setTextColor(text);
            b.setTextSize(19);
            b.setBackgroundColor(Color.TRANSPARENT);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = 0;
            lp.height = 0;
            lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            lp.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            lp.setMargins(dp(4), dp(3), dp(4), dp(3));
            grid.addView(b, lp);
            String digit = k.substring(0,1);
            b.setOnClickListener(v -> number.append(digit));
        }

        Button call = new Button(this);
        call.setText("Call");
        call.setAllCaps(false);
        call.setTextColor(Color.WHITE);
        call.setTextSize(16);
        call.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        call.setBackgroundResource(R.drawable.primary_button_bg);
        root.addView(call, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54)));
        call.setOnClickListener(v -> Toast.makeText(this, "Calling will activate after the mobile VoIP bridge is enabled.", Toast.LENGTH_LONG).show());

        LinearLayout tabs = new LinearLayout(this);
        tabs.setGravity(Gravity.CENTER);
        String[] names = {"Keypad", "Recent", "Team", "Clients"};
        for (String name : names) {
            Button b = new Button(this);
            b.setText(name);
            b.setAllCaps(false);
            b.setTextSize(12);
            b.setTextColor(name.equals("Keypad") ? blue : muted);
            b.setBackgroundColor(Color.TRANSPARENT);
            tabs.addView(b, new LinearLayout.LayoutParams(0, dp(48), 1));
            b.setOnClickListener(v -> Toast.makeText(this, name + " preview", Toast.LENGTH_SHORT).show());
        }
        LinearLayout.LayoutParams tabsLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(48));
        tabsLp.topMargin = dp(10);
        root.addView(tabs, tabsLp);

        setContentView(root);
    }

    @Override
    public void onBackPressed() {
        showLogin();
    }
}

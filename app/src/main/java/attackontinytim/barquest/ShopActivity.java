package attackontinytim.barquest;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.InsertDataValues;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class ShopActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static Hero hero;
    static public int MAIN_RETURN_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        final Activity myAct = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View viewOne;
            View viewTwo;


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                viewOne = inflater.inflate(R.layout.fragment_main, container, false);

                TextView hp_pot_label = (TextView) viewOne.findViewById(R.id.hp_pot_label);
                TextView hp_pot_value = (TextView) viewOne.findViewById(R.id.hp_pot_value);
                TextView hp_pot_quantity = (TextView) viewOne.findViewById(R.id.hp_pot_quantity);

                TextView stat_pot_1_label = (TextView) viewOne.findViewById(R.id.stat_pot_1_label);
                TextView stat_pot_1_value = (TextView) viewOne.findViewById(R.id.stat_pot_1_value);
                TextView stat_pot_1_quantity = (TextView) viewOne.findViewById(R.id.stat_pot_1_quantity);

                TextView stat_pot_2_label = (TextView) viewOne.findViewById(R.id.stat_pot_2_label);
                TextView stat_pot_2_value = (TextView) viewOne.findViewById(R.id.stat_pot_2_value);
                TextView stat_pot_2_quantity = (TextView) viewOne.findViewById(R.id.stat_pot_2_quantity);

                TextView stat_pot_3_label = (TextView) viewOne.findViewById(R.id.stat_pot_3_label);
                TextView stat_pot_3_value = (TextView) viewOne.findViewById(R.id.stat_pot_3_value);
                TextView stat_pot_3_quantity = (TextView) viewOne.findViewById(R.id.stat_pot_3_quantity);

                TextView weapon_label = (TextView) viewOne.findViewById(R.id.weapon_label);
                TextView weapon_value = (TextView) viewOne.findViewById(R.id.weapon_value);
                TextView weapon_quantity = (TextView) viewOne.findViewById(R.id.weapon_quantity);


                //get random potions
                List<ConsumableItem> hpList = ConsumableRepo.getConsumableListByType("Heal");
                List<ConsumableItem> attackList = ConsumableRepo.getConsumableListByType("Attack Up");
                List<ConsumableItem> defenseList = ConsumableRepo.getConsumableListByType("Defense Up");
                List<ConsumableItem> speedList = ConsumableRepo.getConsumableListByType("Speed Up");
                List<ConsumableItem> debuffSpeedList = ConsumableRepo.getConsumableListByType("Monster Speed Debuff");
                List<ConsumableItem> debuffDefenseList = ConsumableRepo.getConsumableListByType("Monster Defense Debuff");
                List<ConsumableItem> debuffAttackList = ConsumableRepo.getConsumableListByType("Monster Attack Debuff");
                List<Weapon> weaponList = WeaponRepo.getAllItems();

                //make a master list of all stat pots
                List<ConsumableItem> statPotList = attackList;
                statPotList.addAll(defenseList);
                statPotList.addAll(speedList);
                //shuffle this new master list.
                Collections.shuffle(statPotList);

                //make a master list of all debuff pots
                List<ConsumableItem> debuffPotList = debuffAttackList;
                debuffPotList.addAll(debuffDefenseList);
                debuffPotList.addAll(debuffSpeedList);
                //shuffle that
                Collections.shuffle(debuffPotList);

                Collections.shuffle(hpList);
                //get  random pots
                final ConsumableItem hpPot = hpList.get(0);
                final ConsumableItem statPot1 = statPotList.get(0);
                Collections.shuffle(statPotList);
                final ConsumableItem statPot2 = statPotList.get(0);
                final ConsumableItem statPot3 = debuffPotList.get(0);
                Collections.shuffle(weaponList);
                final Weapon weapon = weaponList.get(0);


                //set labels to name of item
                hp_pot_label.setText(hpPot.getName());
                stat_pot_1_label.setText(statPot1.getName());
                stat_pot_2_label.setText(statPot2.getName());
                stat_pot_3_label.setText(statPot3.getName());
                weapon_label.setText(weapon.getName());

                //set labels to item cost
                hp_pot_value.setText(String.valueOf(hpPot.getValue()));
                stat_pot_1_value.setText(String.valueOf(statPot1.getValue()));
                stat_pot_2_value.setText(String.valueOf(statPot2.getValue()));
                stat_pot_3_value.setText(String.valueOf(statPot3.getValue()));
                weapon_value.setText(String.valueOf(weapon.getValue()));

                //set quantities to their values
                hp_pot_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(hpPot)));
                stat_pot_1_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot1)));
                stat_pot_2_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot2)));
                stat_pot_3_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot3)));
                weapon_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(weapon)));

                Button HP_Pot_button = (Button) viewOne.findViewById(R.id.HP_Pot_button);
                Button Stat_pot_1_button = (Button) viewOne.findViewById(R.id.stat_pot_1_button);
                Button Stat_pot_2_button = (Button) viewOne.findViewById(R.id.stat_pot_2_button);
                Button stat_pot_3_button = (Button) viewOne.findViewById(R.id.stat_pot_3_button);
                Button weapon_button = (Button) viewOne.findViewById(R.id.weapon_button);

                HP_Pot_button.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                if(hero.getMoney() >= hpPot.getValue()){
                                    hero.setMoney(hero.getMoney() - hpPot.getValue());
                                    InventoryRepo.addItemToInventory(hpPot);
                                    HeroRepo.updateHero(hero);
                                }
                            }
                        }
                );

                Stat_pot_1_button.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                if(hero.getMoney() >= statPot1.getValue()){
                                    hero.setMoney(hero.getMoney() - statPot1.getValue());
                                    InventoryRepo.addItemToInventory(statPot1);
                                    HeroRepo.updateHero(hero);
                                }
                            }
                        }

                );

                Stat_pot_2_button.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                if(hero.getMoney() >= statPot2.getValue()){
                                    hero.setMoney(hero.getMoney() - statPot2.getValue());
                                    InventoryRepo.addItemToInventory(statPot2);
                                    HeroRepo.updateHero(hero);
                                }
                            }
                        }

                );

                stat_pot_3_button.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                if(hero.getMoney() >= statPot1.getValue()){
                                    hero.setMoney(hero.getMoney() - statPot1.getValue());
                                    InventoryRepo.addItemToInventory(statPot1);
                                    HeroRepo.updateHero(hero);
                                }
                            }
                        }
                );

                weapon_button.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                if(hero.getMoney() >= weapon.getValue()){
                                    hero.setMoney(hero.getMoney() - weapon.getValue());
                                    InventoryRepo.addItemToInventory(weapon);
                                    HeroRepo.updateHero(hero);
                                }
                            }
                        }
                );

                return viewOne;
            }

            else {
                viewTwo = inflater.inflate(R.layout.fragment_main_two, container, false);

                // populate weapons list
                final ListView itemList = (ListView) viewTwo.findViewById(R.id.itemList);
                final ArrayList<String> list1 = new ArrayList<String>();

                List<Weapon> wList = InventoryRepo.getAllWeapons();
                for (int i = 0; i < wList.size(); i++) {
                    list1.add(wList.get(i).getName());
                }
                List<ConsumableItem> cList = InventoryRepo.getAllConsumables();
                for (int i = 0; i < cList.size(); i++){
                    list1.add(cList.get(i).getName());
                }

                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, list1);

                itemList.setAdapter(itemsAdapter);

                itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the GridView selected/clicked item text
                        String selectedItemName = parent.getItemAtPosition(position).toString();

                        ConsumableItem selectedItemCon = ConsumableRepo.getConsumableByName(selectedItemName);
                        Weapon selectedItemWep = WeaponRepo.getItemByName(selectedItemName);


                        if (selectedItemCon != null){
                            //then its a consumableitem
                            hero.setMoney(hero.getMoney() + selectedItemCon.getValue());
                            InventoryRepo.subtractItemFromInvetory(selectedItemCon);
                            HeroRepo.updateHero(hero);
                            getActivity().finish();
                            startActivity(getActivity().getIntent());
                        }
                        else {
                            if (InventoryRepo.getItemQuantity(selectedItemWep) == 1) {
                                if (hero.getActive() == selectedItemWep) {
                                    //you can't sell your active item
                                }

                            } else {
                                hero.setMoney(hero.getMoney() + selectedItemWep.getValue());
                                InventoryRepo.subtractItemFromInvetory(selectedItemWep);
                                HeroRepo.updateHero(hero);
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                            }
                        }

                    }
                });

                return viewTwo;
            }
        }


    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Buy";
                case 1:
                    return "Sell";
            }
            return null;
        }
    }
}

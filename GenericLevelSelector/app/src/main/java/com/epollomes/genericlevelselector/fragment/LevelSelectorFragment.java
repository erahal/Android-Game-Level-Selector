package com.epollomes.genericlevelselector.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.epollomes.genericlevelselector.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IOnLevelSelectorFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LevelSelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelSelectorFragment extends Fragment {

    private ViewPager mViewPager;
    private static final int NUMBER_OF_PAGES = 3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView leftArrowImageButton;
    private ImageView rightArrowImageButton;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IOnLevelSelectorFragmentInteractionListener mListener;

    public LevelSelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LevelSelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LevelSelectorFragment newInstance(String param1, String param2) {
        LevelSelectorFragment fragment = new LevelSelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level_selector, container, false);

        mViewPager = (ViewPager) v.findViewById(R.id.level_selector_view_pager);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return SinglePageFragment.newInstance(String.valueOf(position + 1), String.valueOf(position + 1));
            }

            @Override
            public int getCount() {
                return NUMBER_OF_PAGES;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                fixArrowAppearance();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        leftArrowImageButton = (ImageView) v.findViewById(R.id.left_arrow_image_button);
        leftArrowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == 0)
                    return;
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
            }
        });

        rightArrowImageButton = (ImageView) v.findViewById(R.id.right_arrow_image_button);
        rightArrowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == NUMBER_OF_PAGES)
                    return;
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        });


        return v;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fixArrowAppearance();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.on1stFunctionInLevelSelectorFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnLevelSelectorFragmentInteractionListener) {
            mListener = (IOnLevelSelectorFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IOnLevelSelectorFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface IOnLevelSelectorFragmentInteractionListener {
        // TODO: Update argument type and name
        void on1stFunctionInLevelSelectorFragmentInteraction(Uri uri);
    }


    private void fixArrowAppearance() {
        int currentPageNumber = 0;
        currentPageNumber = mViewPager.getCurrentItem();

        if (currentPageNumber == 0) {
            leftArrowImageButton.setEnabled(false);
            leftArrowImageButton.setVisibility(View.INVISIBLE);

            rightArrowImageButton.setEnabled(true);
            rightArrowImageButton.setVisibility(View.VISIBLE);
        } else if (currentPageNumber == (NUMBER_OF_PAGES - 1)) {
            leftArrowImageButton.setEnabled(true);
            leftArrowImageButton.setVisibility(View.VISIBLE);

            rightArrowImageButton.setEnabled(false);
            rightArrowImageButton.setVisibility(View.INVISIBLE);
        } else {
            rightArrowImageButton.setEnabled(true);
            rightArrowImageButton.setVisibility(View.VISIBLE);

            leftArrowImageButton.setEnabled(true);
            leftArrowImageButton.setVisibility(View.VISIBLE);
        }
    }


}

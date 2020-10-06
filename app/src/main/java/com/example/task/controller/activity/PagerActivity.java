package com.example.task.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.task.R;
import com.example.task.adapter.TaskViewPagerAdapter;
import com.example.task.controller.fragment.NewTaskDialogFragment;
import com.example.task.controller.fragment.TaskDetailDialogFragment;
import com.example.task.controller.fragment.TaskViewPagerFragment;
import com.example.task.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagerActivity extends AppCompatActivity implements NewTaskDialogFragment.OnNewTaskListener {
    public static final String NEW_TASK_DIALOG_FRAGMENT = "task dialog fragment";
    public static final String TASK_DETAIL_DIALOG_FRAGMENT = "task detail dialog fragment";
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        findViews();

        setViewPagerAdapter();

        new TabLayoutMediator(mTabLayout, mViewPager,
                (mTabLayout, position) -> mTabLayout.setText(setTabText(position))).attach();

        setClickListeners();
    }

    private void setViewPagerAdapter() {
        TaskViewPagerAdapter taskViewPagerAdapter = new TaskViewPagerAdapter(this);
        mViewPager.setAdapter(taskViewPagerAdapter);
    }

    private void setClickListeners() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskDialogFragment newTaskDialogFragment = NewTaskDialogFragment.newInstance();
                newTaskDialogFragment.show(getSupportFragmentManager(), NEW_TASK_DIALOG_FRAGMENT);
                /*String name = mTaskRepository.getTasks().get(0).getName();
                Task task = new Task(name, StarterFragment.getState());
                mTaskRepository.addTask(task);
                Snackbar.make(v, "The new task is added to " + task.getTaskState() + " tab",
                        Snackbar.LENGTH_LONG).show();
                updateRecyclerView();*/
            }
        });


    }

    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFloatingActionButton = findViewById(R.id.floating_action_button);


    }

    private String setTabText(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "TODO";
                break;
            case 1:
                title = "Doing";
                break;
            case 2:
                title = "Done";
                break;
        }
        return title;
    }

    @Override
    public void updateRecyclerView() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskViewPagerFragment)
                ((TaskViewPagerFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }

    /*private void updateRecyclerView() {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            if (getSupportFragmentManager().getFragments().get(i) instanceof TaskViewPagerFragment)
                ((TaskViewPagerFragment) getSupportFragmentManager().getFragments().get(i)).updateUI();
        }
    }*/
}


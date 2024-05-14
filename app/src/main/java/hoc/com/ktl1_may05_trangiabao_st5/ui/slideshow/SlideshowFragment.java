    package hoc.com.ktl1_may05_trangiabao_st5.ui.slideshow;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;

    import hoc.com.ktl1_may05_trangiabao_st5.databinding.FragmentSlideshowBinding;

    public class SlideshowFragment extends Fragment {

        private FragmentSlideshowBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            SlideshowViewModel slideshowViewModel =
                    new ViewModelProvider(this).get(SlideshowViewModel.class);

            binding = FragmentSlideshowBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final TextView textView = binding.textSlideshow;
            slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
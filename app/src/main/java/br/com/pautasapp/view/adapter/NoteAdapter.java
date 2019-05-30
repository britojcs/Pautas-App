package br.com.pautasapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import br.com.pautasapp.R;
import br.com.pautasapp.model.entity.Note;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private Context context;
    private StatusOnClickListener statusOnClickListener;
    private static int currentPosition = -1;
    private boolean openNotes;

    public NoteAdapter(boolean openNotes) {
        super(DIFF_CALLBACK);
        this.openNotes = openNotes;
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getShortDescription().equals(newItem.getShortDescription()) &&
                    oldItem.getDetails().equals(newItem.getDetails()) &&
                    oldItem.isOpen() == newItem.isOpen() &&
                    oldItem.getIdUser() == newItem.getIdUser();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.txtTitle.setText(currentNote.getTitle());
        holder.txtShortDescription.setText(currentNote.getShortDescription());
        holder.txtDetails.setText(String.valueOf(currentNote.getDetails()));

        if (currentPosition == position) {
            boolean down = holder.lytExpandable.getVisibility() == View.GONE;
            Animation slideAnimation = AnimationUtils.loadAnimation(context, down ? R.anim.slide_down : R.anim.slide_up);
            holder.lytExpandable.startAnimation(slideAnimation);
            slideAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (down)
                        holder.lytExpandable.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (!down)
                        holder.lytExpandable.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else
            holder.lytExpandable.setVisibility(View.GONE);
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtShortDescription)
        TextView txtShortDescription;
        @BindView(R.id.txtDetails)
        TextView txtDetails;
        @BindView(R.id.txtAuthor)
        TextView txtAuthor;
        @BindView(R.id.lytTitle)
        LinearLayout lytTitle;
        @BindView(R.id.lytExpandable)
        LinearLayout lytExpandable;
        @BindView(R.id.btnStatus)
        MaterialButton btnStatus;

        NoteHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            lytTitle.setOnClickListener(view1 -> {
                currentPosition = getAdapterPosition();
                notifyDataSetChanged();
            });

            btnStatus.setText(openNotes ? R.string.close : R.string.reopen);

            btnStatus.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (statusOnClickListener != null && position != RecyclerView.NO_POSITION)
                    statusOnClickListener.onClick(getItem(position));
            });
        }
    }

    public interface StatusOnClickListener {
        void onClick(Note note);
    }

    public void setStatusOnClickListener(StatusOnClickListener statusOnClickListener) {
        this.statusOnClickListener = statusOnClickListener;
    }

}
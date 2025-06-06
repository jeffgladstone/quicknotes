import React, { useState } from "react";

export default function NoteList({ notes, onUpdate, onDelete }) {
  const [editId, setEditId] = useState(null);
  const [editTitle, setEditTitle] = useState("");
  const [editContent, setEditContent] = useState("");

  const startEdit = (note) => {
    setEditId(note.id);
    setEditTitle(note.title);
    setEditContent(note.content);
  };

  const submitEdit = (e) => {
    e.preventDefault();
    onUpdate(editId, { title: editTitle, content: editContent });
    setEditId(null);
  };

  return (
    <div>
      {notes.map((note) => (
        <div key={note.id}>
          {editId === note.id ? (
            <form onSubmit={submitEdit}>
              <input
                value={editTitle}
                onChange={(e) => setEditTitle(e.target.value)}
                required
              />
              <input
                value={editContent}
                onChange={(e) => setEditContent(e.target.value)}
                required
              />
              <button type="submit">
                Save
              </button>
            </form>
          ) : (
            <>
              <h2>{note.title}</h2>
              <p>{note.content}</p>
              <button onClick={() => startEdit(note)}>
                Edit
              </button>
              <button onClick={() => onDelete(note.id)}>
                Delete
              </button>
            </>
          )}
        </div>
      ))}
    </div>
  );
}

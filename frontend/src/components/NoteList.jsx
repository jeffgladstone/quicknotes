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
        <div key={note.id} className="border p-3 mb-2">
          {editId === note.id ? (
            <form onSubmit={submitEdit} className="mb-2">
              <input
                value={editTitle}
                onChange={(e) => setEditTitle(e.target.value)}
                className="border p-1 mr-2"
              />
              <input
                value={editContent}
                onChange={(e) => setEditContent(e.target.value)}
                className="border p-1 mr-2"
              />
              <button type="submit" className="bg-green-500 text-white px-2 py-1 rounded">
                Save
              </button>
            </form>
          ) : (
            <>
              <h2 className="font-semibold">{note.title}</h2>
              <p>{note.content}</p>
              <button
                onClick={() => startEdit(note)}
                className="text-blue-500 mr-2"
              >
                Edit
              </button>
              <button
                onClick={() => onDelete(note.id)}
                className="text-red-500"
              >
                Delete
              </button>
            </>
          )}
        </div>
      ))}
    </div>
  );
}

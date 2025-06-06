import React, { useEffect, useState } from "react";
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import noteService from "./services/noteService";

export default function App() {
  const [notes, setNotes] = useState([]);
  
    useEffect(() => {
      fetchNotes();
    }, []);

  const fetchNotes = async () => {
    const res = await noteService.getAll();
    setNotes(res);
  };

  const handleCreate = async (note) => {
    await noteService.create(note);
    fetchNotes();
  };

  const handleUpdate = async (id, note) => {
    await noteService.update(id, note);
    fetchNotes();
  };

  const handleDelete = async (id) => {
    await noteService.remove(id);
    fetchNotes();
  };

  return (
    <div>
      <h1>QuickNotes</h1>
      <NoteForm onSubmit={handleCreate} />
      <NoteList notes={notes} onUpdate={handleUpdate} onDelete={handleDelete} />
    </div>
  );
}
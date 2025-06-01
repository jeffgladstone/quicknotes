const API_BASE = "http://localhost:8080/api/notes";

const getAll = async () => {
  const res = await fetch(API_BASE);
  return res.json();
};

const create = async (note) => {
  const res = await fetch(API_BASE, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note),
  });
  return res.json();
};

const update = async (id, note) => {
  const res = await fetch(`${API_BASE}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note),
  });
  return res.json();
};

const remove = async (id) => {
  await fetch(`${API_BASE}/${id}`, { method: "DELETE" });
};

export default { getAll, create, update, remove };